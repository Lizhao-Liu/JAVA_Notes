
# Code Quality

> How easy is it for others to understand?
> How easy is it for others to change?
> Does it support long-term maintenance?

## Method Complexity

**Measuring Method Complexity**
- Measuing the length of methods(in lines)
- Counting the number of parameters passed in
- Depth of nesting(number of levels of indentation)
- "Cyclomatic Complexity"
> Cyclomatic Complexity: Flow Graph
![image](https://github.com/Lizhao-Liu/JAVA_Notes/blob/main/code_quality/Screenshot%202021-03-06%20at%2015.32.42.png)
<div align="center"> CC = E - N + 2 </div>
Example:

```java
String[] folderContent;      // Initialised elsewhere
Colour[] originalColours;    // Initialised elsewhere
Colour[] replacementColours; // Initialised elsewhere

for (String filename: folderContent) {
    if((filename.endswith(".jpg")) || (filename.endswith(".JPG")) || (filename.endswith(".png")) || (filename.endswith(".PNG"))) {
        Colour[][] pixelData = loadPixelData(filename);
        for(int x=0; x<pixelData.length ;x++) {
            for(int y=0; y<pixelData[0].length ;y++) {
                for(int i=0; i<originalColours.length ;i++) {
                    if(pixelData[x][y] == originalColours[i]) {
                        pixelData[x][y] = replacementColours[i];
                    }
                }
            }
        }
        savePixelData(pixelData, filename);
    }
}
```
Possible improvements:
1. Put the tests for image exention into a method called `isImageFile()`
2. Write a method called `replaceAllPixels` that took the PixelData as a parameter and does all of the looping
3. Use a method called `replaceSinglePixel`
4. Use a better lookup table such as a hash map

## Good Naming
- Anything less than 5 chars is probably too short
- Anything greater than 20 chars is getting a bit long Single words are often not enough 
- camelCaseIsTheRecommendedStyle
- Variables should describe the data that they hold
- Verb/Subject names for methods

## Replication
> Achieved by "factoring out" common functionality Referred to as "DRY" (Don't Repeat Yourself) code...

<div align="center"> DRY Metrics </div>

- IF density: Large blocks entirely of IF statements 
- Line similarity: Similar duplicated lines of code
- Method similarity: "Self plagiarism" of methods


[naive implementation](https://github.com/drslock/JAVA2020/blob/main/Weekly%20Workbooks/05%20Code%20Quality/code/NaiveCalculator/NaiveCalculator.java)

[ more refined solution ](https://github.com/drslock/JAVA2020/blob/main/Weekly%20Workbooks/05%20Code%20Quality/code/RefinedCalculator/RefinedCalculator.java)

 **Avoid Redundant Code**
 
 
