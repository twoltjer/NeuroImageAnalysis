# NeuroImageAnalysis
Java program for analyzing how positive a stain is on sets of images from brain slides.

## How to use this program

NOTE: The commands in this section are for bash shells or similar. If using Windows, you might need to find a different way to compile and run the program. 

To run this program, the following commands must be run from the repository's parent directory:
```
mkdir bin
javac src/Main.java src/*/*.java -d bin
cd bin
java Main
```
To clean up, simply remove the bin folder:
```
rm -rv bin
```
Make sure that you're actually in the project's directory when deleting the bin folder, as other "bin" folders might be very important not to delete.

## Documentation
The documentation for this project is a little out of date, but can be found in the "doc" folder. I'll be updating it soon. :)
