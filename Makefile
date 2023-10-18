ARG = ""

build:
	@mvn package
	@echo "Build complete."

clean:
	@mvn clean
	@echo "Clean complete."

run:
	@java -cp target/assignment2-1.0-SNAPSHOT.jar com.assignment2.robi.Main $(ARG)
