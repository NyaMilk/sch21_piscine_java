1. Make target directory:
	mkdir target

2. Compile files:
	javac -d target $(find -name "*.java")

3. Run:
	java -cp target edu.school21.printer.app.Program --white=[symbol for while color] --black=[symbol for black color] [absolute path to bmp image]

Example:
	java -cp target edu.school21.printer.app.Program --white=. --black=o absolute/path/image.bmp
