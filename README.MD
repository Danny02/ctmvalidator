# OpenCTM Validator

This is a small tool to quickly validate a CTM model File.

[![Build Status](https://travis-ci.org/Danny02/ctmvalidator.svg?branch=develop)](https://travis-ci.org/Danny02/ctmvalidator)

# release types
* Unix executable
* standalone JAR

## usage
`ctm-validator testfile.ctm`

will produce output like:

```
Reading model from: 'testfile.ctm'
Info:
        - file comment:                  some model experiment
        - triangle count:                15744
        - vertex count:                  7958
        - has normals:                   true
        - uv attributes:                 3
        -       name:                         default
        -               material name:               some-texture
        -               precision:                   2.4414062E-4
        - other attributes:              1
        -       name:                         Color
        -               precision:                   0.00390625
```
