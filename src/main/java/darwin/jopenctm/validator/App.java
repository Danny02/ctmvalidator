package darwin.jopenctm.validator;

import darwin.jopenctm.data.AttributeData;
import darwin.jopenctm.data.Mesh;
import darwin.jopenctm.errorhandling.BadFormatException;
import darwin.jopenctm.errorhandling.InvalidDataException;
import darwin.jopenctm.io.CtmFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Daniel Heinrich on 09/03/2017.
 */
public class App {

    public static void main(String... args) {
        for (String arg : args) {
            validateModelFromPath(arg);
        }
    }

    private static void validateModelFromPath(String path) {
        try {
            Path p = Paths.get(path);
            CtmFileReader reader = new CtmFileReader(Files.newInputStream(p));
            readAndValidateModel(path, reader);
        } catch (InvalidPathException ex) {
            System.err.println("'" + path + "' is not a valid path to a file!");
        } catch (IOException ex) {
            System.err.println("can not read file '" + path + "'");
        }
    }

    private static void readAndValidateModel(String path, CtmFileReader reader) {
        List<String> validationError;
        try {
            System.out.println("\nReading model from: '" + path + "'");
            Mesh mesh = reader.decodeWithoutValidation();
            printModelInfo(mesh, reader.getFileComment());
            validateModel(mesh);
        } catch (BadFormatException | IOException | InvalidDataException ex) {
            System.err.println("error decoding model from '" + path + "':\n\t - " + ex.getMessage());
        }
    }

    private static void printModelInfo(Mesh mesh, String comment) {
        System.out.println("Info:");
        printValue("file comment:", comment);
        printValue("triangle count:", mesh.getTriangleCount());
        printValue("vertex count:", mesh.getVertexCount());
        printValue("has normals:", mesh.hasNormals());
        if (mesh.getUVCount() > 0) {
            printValue("uv attributes:", mesh.getUVCount());
            for (int i = 0; i < mesh.getUVCount(); i++) {
                AttributeData d = mesh.texcoordinates[i];
                printValue("\tname:", d.name);
                printValue("\t\tmaterial name:", d.materialName);
                printValue("\t\tprecision:", d.precision);
            }
        }
        if (mesh.getAttrCount() > 0) {
            printValue("other attributes:", mesh.getAttrCount());
            for (int i = 0; i < mesh.getAttrCount(); i++) {
                AttributeData d = mesh.attributes[i];
                printValue("\tname:", d.name);
                printValue("\t\tprecision:", d.precision);
            }
        }
    }

    private static void printValue(String name, Object value) {
        System.out.printf("\t- %-30s %s\n", name, value);
    }

    private static void validateModel(Mesh mesh) {
        List<String> errors = mesh.validate();
        if (!errors.isEmpty()) {
            System.out.println("Validation Errors:");
            for (String e : errors) {
                System.out.println("\t- " + e);
            }
        }
    }
}
