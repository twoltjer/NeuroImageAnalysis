# NeuroImageAnalysis

Java program for analyzing how positive a stain is on sets of images from brain slides. 

Note: this will continue to be the purpose of the program for at least the 2.0 series of releases. However, I do hope that one day this program may be more flexible. 

## How to use this program

For the older 1.* releases of this program, see the older readme files. The below describes the alpha builds of version 2.0.

One of the many core changes in the program set to release in version 2.0 is the use of an Apache ANT build file. An experimental version of this file is included in the project, and should work relatively well. To run the binaries built by ANT, either execute the JAR file in the "dists" directory. 

Alternatively, in case you don't have ant and don't want to install it, or you want to compile traditionally, compile the files in the "src" directory, including the JAR files in the lib directory in the classpath, and run it--again, with the adjusted classpath--pointing at com.nia.control.Main as the run class. 

Some users may with to compile using ANT and run using the build files (found in the "build" directory). The classpath may not need to be adjusted for this, but it is recommended that you do so anyway to avoid any errors that you may encounter if you run the program using this approach. (Mind you, I don't see any reason why anyone would.)

## Documentation
The documentation for this project is currently rather outdated. For this reason, the "doc" directory has been removed for the time being. Once release 2.0 of the code is ready for release, the documentation should follow shortly after, in release 2.0.1. If you wish to read the old documentation, download an older commit or release.

## Licensing
Packaged along with the project (in the "lib" directory) is a JAR archive with binary code from Sun Microsystems, Inc. The licences for the packaged archive are in the directory title "LICENSES." If there is trouble with this usage of the binary code, I ask that you contact me and I'll remove it from the project as soon as possible. I believe that, with this arrangement, my program is easier for users to use, and that Sun gets the credit they deserve for their contribution to the project.
