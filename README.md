# Wavefront To POV-Ray 1.0.0

![image](https://github.com/NeuralCortex/Wavefront_To_Pov/blob/main/images/w2p.png)

## Funktionsweise des Programms

Das Programm ist ein JavaFX-Projekt welches aus [Blender](https://en.wikipedia.org/wiki/Blender_(software)) exportierte [Wavefront](https://en.wikipedia.org/wiki/Wavefront_.obj_file) *.obj-Dateien in POV-Ray *.pov oder *.inc-Dateien umwandelt.</br>
Bei der Umwandlung werden auch mehrere Objekte in der *.obj-Datei in mehrere POV-Ray Objekte konvertiert.</br>
Das Programm wurde getestet mit Blender 3.6

## How the program works

The program is a JavaFX project which from [Blender](https://en.wikipedia.org/wiki/Blender_(software)) exported [Wavefront](https://en.wikipedia.org/wiki/Wavefront_.obj_file ) *.obj files converts into POV-Ray *.pov or *.inc files.</br>
The conversion also converts multiple objects in the *.obj file into multiple POV-Ray objects.</br>
The program was tested with Blender 3.6

## Export-Einstellungen Blender

1. Include
    1. [x] OBJ Objects

2. Geometry
    1. [x] Apply Modifiers
    2. [x] Write Normals
    3. [x] Include UVs
    4. [x] Triangulate Faces
    5. [x] Keep Vertex Order

## Export Settings Blender

1. Include
    1. [x] OBJ Objects

2. Geometry
    1. [x] Apply Modifiers
    2. [x] Write Normals
    3. [x] Include UVs
    4. [x] Triangulate Faces
    5. [x] Keep Vertex Order

## Hinweis

Eine kovertierte Datei sollte möglichst als *.inc gespeichert werden.</br>
Durch einbinden der *.inc-Datei in der POV-Ray *.pov-Datei ist es möglich das jeweilige Objekt mit "object{object-name ...modifiers...}" anzusprechen.</br>
Bei dieser Methode bleibt die *.pov-Datei sehr übersichtlich.

## A notice

A converted file should be saved as *.inc if possible.</br>
By including the *.inc file in the POV-Ray *.pov file, it is possible to address the respective object with "object{object-name ...modifiers...}".</br>
With this method, the *.pov file remains very clear.

## Verwendete Technologie

Dieses JavaFX-Projekt wurde erstellt mit der Apache NetBeans 17 IDE [NetBeans 17](https://netbeans.apache.org/).

Folgende Frameworks sollten installiert sein:

- JAVA-SDK [JAVA 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
- SceneBuilder für GUI-Entwicklung [Gluon SceneBuilder](https://gluonhq.com/products/scene-builder/)
- JAVA-FX-SDK [JavaFX](https://gluonhq.com/products/javafx/)

## Technology used

This JavaFX project was built with the Apache NetBeans 17 IDE [NetBeans 17](https://netbeans.apache.org/).

The following frameworks should be installed:

- JAVA SDK [JAVA 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
- SceneBuilder for GUI development [Gluon SceneBuilder](https://gluonhq.com/products/scene-builder/)
- JAVA FX SDK [JavaFX](https://gluonhq.com/products/javafx/)
