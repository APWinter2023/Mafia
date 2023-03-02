tester:
	rm tester.zip 2> /dev/null || true
	zip -r tester.zip test valid_files tester_config.json

solution:
	rm solution.zip 2> /dev/null || true
	cd src && zip -r ../solution.zip *
