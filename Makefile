tester:
	zip -r test.zip test valid_files tester_config.json

solution:
	cd src && zip -r ../solution.zip *
