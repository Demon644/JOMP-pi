# JOMP-pi
Calculate pi with JOMP

## How to use
0.To change the code, edit the file with .jomp extension.

1.Compile the .jomp file into .java with this command
```
java -cp jomp.jar jomp.compiler.Jomp PiJomp
```
If everything is ok, you will get this output
```
Jomp Version 1.0.beta.
Compiling class PiRed....
Parallel For Directive Encountered
```
2.Compile the .java file into .class
```
javac -cp jomp.jar PiJomp.java
```
3.Run it with this command
```
java -cp jomp.jar:. -Djomp.threads=* PiJomp
```
Write the number of threads where the star is. By default it should use as much threads as you have.

There is a good description of what JOMP can do on this website:
https://www.researchgate.net/publication/2644232_JOMP-an_openMP-like_interface_for_Java
