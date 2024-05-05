# Fractal flames generator

Generator can create fractal flame image using several threads.

**Prerequisites: JDK-21**

## Getting started

1. Build the project

```bash
./gradlew uberJar
```

2. Run project

```bash
java -jar fractal-flame-generator-1.0-SNAPSHOT-uber.jar ...options
```

## CLI options

**1. The number of threads**

Number of threads will be used

```bash
java -jar ... --threads-number=number ...
```

**2. The number of points**

Number of points

```bash
java -jar ... --points-number=number ...
```

**3. The number of iterations**

Number of point transformation iterations

```bash
java -jar ... --iterations-number=number ...
```

**4. The number of affines**

Number of random affine transformations

```bash
java -jar ... --affines-number=number ... 
```

**5. Gamma**

Gamma parameter for log gamma correction

```bash
java -jar ... --gamma=number ... 
```

**6. Symmetry**

Number of symmetric copies

```bash
java -jar ... --symmetry=number ... 
```

**7. Path**

Path and file name for file with generated fractal flame

```bash
java -jar ... --path=path ... 
```

**8. Colors**

Comma separated list of colors will be used

```bash
java -jar ... --colors="WHITE, ORANGE" ... 
```

**9. Non-linear transformation**

Non linear transformation which will be used to generate flames

```bash
java -jar ... --nonlinear=EXPONENTIAL ... 
```