# False Email Detector Using Java

## Project Overview
Name-AKSHAT GROVER
Reg no.-25BAI10763

False Email Detector is a small command-line Java project that checks an email address and identifies whether it looks invalid,valid in formator suspicious.

The project is designed to be simple to run from a terminal. It does not need a database, external library, API, or internet connection.

The detector checks things such as:
- Basic email format
- Username and domain structure
- Unusual dot patterns
- Very long username or domain
- Common temporary or disposable email domains
- Some patterns that can indicate automatically generated addresses
- Common email providers

The program does not claim that an email account definitely exists. It only checks the information that can be identified locally from the address itself.

## Files

- `FalseEmailDetector.java` - Main Java source code
- `sample_test_cases.txt` - Example inputs for testing
- `PROJECT_REPORT.docx` - Project report

## Requirements

- Java JDK 8 or later
- Command Prompt, PowerShell, Terminal, or any Java-supported terminal

No additional Java packages are required.

## Important Limitation

An email can have a correct format without actually being registered. This project does not connect to a mail server or send a verification message. Therefore, its result should be understood as a local screening result rather than proof that an email account exists.

## GitHub Submission

Before submitting:

1. Create a GitHub repository.
2. Make the repository Public.
3. Upload the project files to the root of the repository.
4. Make sure `README.md` is also in the root.
5. Submit only the repository root URL.


