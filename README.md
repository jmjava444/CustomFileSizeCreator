# CustomFileSizeCreator (Alpha Release!)
 Computer application made in Java: Creates a \*.test file of the user's desired size. Useful for testing a new flash drive or hard drive to see the read and write speeds on it.
 
 ![Main UI Window](https://i.imgur.com/6N849UG.png)

I utilized the Java swing library and file chooser to make the GUI for it. This application has been tested on Windows and Linux, however it should run on Mac too if you have a recent version of Java installed on your PC. This application supports multi-threading to update the progress bar as the file is being written to disk. I made this project for fun, and thus it has no warranty. Use at your own risk. I would appreciate any feedback that you give.

## How To:
Navigate to *CustomFileSizeCreator\out\artifacts\custom_file_size_creator_jar* and open the .jar file to run the application. It is portable, so no need for installation. If you do not know how to execute a .jar file, maybe look here to start. https://www.wikihow.com/Run-a-.Jar-Java-File


The main window has a text field where you can enter how big you want the file to be. Choose from the drop-down menu what unit of file size you need and click save. Choose a file location and a name for your file. (Don't worry about adding an extension.) A loading bar will appear on screen to show you the current progress, and a message pop-up will show when the file is done. 

I'm not sure what the limit is on making big files, but I capped it to be a maximum of 64GB which is reasonable enough. I don't know why you'd need that big of a file anyways.

## Windows 11 Screenshots:
![Main UI Window](https://i.postimg.cc/Pf8R6gt6/Screenshot-2022-06-13-151620.png)

![File Drop-Down Menu](https://i.postimg.cc/7LWK9Vj8/Screenshot-2022-06-13-152127.png)

![Enter File Size](https://i.postimg.cc/kXQhCWRK/Screenshot-2022-06-13-151736.png)

![Change Units](https://i.postimg.cc/4NkFjWyX/Screenshot-2022-06-13-151826.png)

![Loading Bar Appears](https://i.postimg.cc/s2Xn5864/Screenshot-2022-06-13-152220.png)

![File Saved Successfully](https://i.postimg.cc/BnJ7nt2y/Screenshot-2022-06-13-152321.png)

![Error Message](https://i.postimg.cc/P5K3ph4q/Screenshot-2022-06-13-152406.png)

## Linux Screenshots:
![Main UI Window](https://i.imgur.com/YF7q1JO.png)

![Loading Creating the File](https://i.imgur.com/zdARqDo.png)

![File is completed](https://i.imgur.com/sIktVEK.png)
