# NeuroImageAnalysis
Java program for analyzing how positive a stain is on sets of images from brain slides.

## How to use this program

NOTE: The commands in this section are for bash shells or similar. If using Windows, you might need to find a different way to compile and run the program. 

To run this program, the following commands must be run from the repository's parent directory:
```
mkdir bin
javac src/Main.java src/*/*.java -d bin -cp lib/jai_imageio.jar
cd bin
java -cp ../lib/jai_imageio.jar:. Main [path/to/folder]
```
As you can see, the execution command ("java -cp ...") has an optional command-line argument that lets it target a folder before even starting the program. This allows the user to bypass the folder selection if desired.

To clean up, simply remove the bin folder:
```
rm -rv bin
```
Make sure that you're actually in the project's directory when deleting the bin folder, as other "bin" folders might be very important not to delete.

## Documentation
The documentation of this project should be relatively up to date. Some minor changes may have occurred since it was generated, but nothing major has changed.

## Licensing
Packaged along with the project (in the "lib" directory) is a jar archive with binary code from Sun Microsystems, Inc. The licences for the packaged archive are in the directory title "LICENSES." If there is trouble with this usage of the binary code, I ask that you contact me and I'll remove it from the project as soon as possible. I believe that, with this arrangement, my program is easier for users to use, and that Sun gets the credit they deserve for their contribution to the project.
