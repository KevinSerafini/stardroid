The following procedure will regenerate the binary star data:
  1.  Install protoc from https://developers.google.com/protocol-buffers/docs/downloads
  1.  Regenerate the Java protocol buffer with `./compile_proto.sh`.  This step and the one above can be skipped if no changes to the app's source.proto file have been made. 
  1.  Build the utilities with `./gradlew assemble` from the stardroid directory.
  1.  Convert the data files to text protocol buffers with `./generate.sh` from the tools directory.
  1.  Recompile the App to ensure that string IDs are up to date.  You can do this just by running `./gradlew assemble`.
  1.  Replace the text protocol buffers with versions that include the string resource ids from the `R.java` file with `./rewrite.sh` from the tools directory
  1.  Finally run `./binary.sh` from the tools directory to convert the ascii proto bufs to binary ones (and put them in the right directory).
