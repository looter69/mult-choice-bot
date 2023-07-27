# Multiple Choice Bot
A simple program that allows Users to input a list of questions and corresponding answers. The program will pick and ask a random question and checks if the User's input is correct.

## Features
1. Read custom List of Questions
2. Picking Question at Random
3. (Randomising response options)

## To add Questions
For each Question-Answer-Combo:
```
[Q] : [A1] : [A2] : [A3] : [A4] : i : j

Q = Question
A1-A4 = Possible responses (One of which is correct)
i = Indices of the correct response (1-4)
j = How many times a Question has to be answered correctly before it's removed from the List

```
Save your Question list as ```Questions.txt```!

### Important
- Make sure none of your Questions or Answers contain the ```:``` symbol. This is used as a formatting character!
- If parsing a Question fails, you will find the corresponding Line mentioned in your console output.

## Example 
```
Who created this program? : Option 1 : github.com/looter69 : Firefly : Bill Gates : 23 : 3
```

# Changelog
## V1.2 | Stylized GUI
- GUIs are displayed by default. If you prefer using the Terminal, change the ```useGui```-flag in the ```Main.java``` file.
- Progress is only saved when you hit the ```SAVE```-Button. Hitting the ```X``` on the Window won't save the progress.

## V1.1 | Support for multiple correct answers
- Now more than one answer can be correct

## V1 | Initial Release
- Reading and Writing from ```Questions.txt``` file
- Questions are no longer asked after being correctly answered three times in the past

# Roadmap
- Support for multiple languages
- Settings File for more customization

###### Version 1.2 | by Firefly | github.com/looter69