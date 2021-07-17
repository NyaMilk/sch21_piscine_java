1. Make target directory:
	mkdir target

2. Copy resources:
	cp -R src/resources target

3. Complile:
	javac -d target $(find ./src -name "*.java")

4. Make jar:
	jar cfm target/images-to-char-printer.jar src/manifest.txt -C target .

5. Run:
	java -jar target/images-to-char-printer.jar --white=[symbol for while color] --black=[symbol for black color]

Example:
    java -jar target/images-to-char-printer.jar --white=. --black=o
