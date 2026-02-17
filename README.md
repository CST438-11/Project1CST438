# Project 01 Retrospective and overview

[Video Walkthrough]() 
<!-- Ads have really ruined rick-rolling. -->
[Github Repo](https://github.com/CST438-11/Project1CST438)

## Overview
This is an app that fetches up to date currency conversion rates using this API [here](https://www.exchangerate-api.com/).

We got styling help for this document from [this guide](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)

## Introduction

* Communcation was managed mostly in Slack channel (We made a group chat for our team)
* Initially we had 15 issues, but eventually added more which became 17
* Currently completed 11 issues 

## Team Retrospective

### Team Member Names (Neil C, Brandon A, Kristopher C, Aidan S)
- Neil Cabanilla
- Brandon Algarra
- Kristopher Church
- Aidan Shanley

### Neil Cabanilla
- [Neil C. Pull Request](https://github.com/CST438-11/Project1CST438/pulls/neilcabanilla)
- [Neil C. Issues](https://github.com/CST438-11/Project1CST438/issues?q=is%3Aissue%20state%3Aopen%20author%3Aneilcabanilla)

#### My roles were to create the functionality for the SignUp Page

+ What was the biggest challenge?
  + The biggest challenge to me was creating the ViewModel for the SignUpPage
+ Why was it a challenge?
  + It was a challenge because it was my first time dong it and my first time hearing about it.
+ How was the challenge addressed?
  + I addresed the challenged by using youtube videos and watching tutorials how a ViewModel works.
  + In addition, going to stackoverflow if there were people asking similar questions such as "What is a ViewModel". 
+ Favorite / most interesting part of this project?
  + Favorite thing that I liked to do in this project was connecting the database to the signup and actually inserting users into the database. Basically, seeing how everything connect to one another.
+ If you could do it over, what would you change?
  + One thing I would change is making a different file for the Activity & the Compose since that is the best practice, unfortunately I did the opposite and created them in a single file
+ What is the most valuable thing you learned?
  + The most valuable thing I learned is communcation is a big thing in group project because without it, it could get messy and confusion starts to happen.

### Brandon Algarra
- [Brandon A. Pull Request](https://github.com/CST438-11/Project1CST438/pulls/BAlgarra)
- [Brandon A. Issues](https://github.com/CST438-11/Project1CST438/issues?q=is%3Aissue%20state%3Aopen%20assignee%3ABAlgarra)

#### What was your role / which stories did you work on
My role was to create the initial user stores and issues, design the homepage and exchange rate page, and implement the api to fetch data to display on the exchange rate page.

+ What was the biggest challenge? 
  + Understanding how to observe the ViewModel and recompose the UI accordingly in jetpack compose. 
+ Why was it a challenge?
  + It was a different structure than what I was used to, and being able to fully take advantage of it's features took much more than I originally understood.
+ How was the challenge addressed?
  + Following the Mars Photo App tutorial, multiple video tutorials, and reading the composable documentation.
+ Favorite / most interesting part of this project
  + Saving to user table for logged in user, and being able to use that data on another screen.
+ If you could do it over, what would you change?
  + Try to schedule and gauge issues better using yeterday's weather. In the beginning we allotted more time than was necessary to issues that ended up being not as critical.
+ What is the most valuable thing you learned?
  + Understand the why's when following a tutorial, communicate to ensure pages interact properly.

### Kristopher Churdch
- [Kristopher C. Pull Request](https://github.com/CST438-11/Project1CST438/pulls/KristopherLC)
- [Kristopher C. Issues](https://github.com/CST438-11/Project1CST438/issues?q=is%3Aissue%20state%3Aopen%20assignee%3AKristopherLC)
 
#### Data Layer, View & Test Layer for LoginActivity
I worked on creating the data layer which includes defining the database and how to manipulate its data. Then I integrated the database into the login activity and produced tests to verify the integrity of this.

+ What was the biggest challenge? 
  + Knowing proper project directory structure. This was my first time learning about things like view models, repositorys, view model factories, etc and the challenge was to know all of the files I needed and where to put them in the project while adhering to scalable practices. Also writing comments is kind of tough because I get lazy and don't think about my future self.
+ Why was it a challenge?
  + Lack of previous experience. Every time I'd get information on the first step, I'd have questions about how to do that which would lead me to more questions.
+ How was the challenge addressed?
  + I used web resources, collaborated with team members, and asked ChatGPT on what best practices typically look like.
+ Favorite / most interesting part of this project
  + I actually really liked making the data layer. It gave me an understanding of how a client side database is built, though I would like to know how server side would work next.
+ If you could do it over, what would you change?
  + Mabye I'd use a loginRoute file, but I'm not sure what that is yet. Also I'd be interested in further enhancing the database such as adding hashed passwords. Basically create a proper trust boundary.
+ What is the most valuable thing you learned?
  + Everything. I literally didn't even know how to code in kotlin in the beginning and had completely forgotten what DAOs were. So in conclusion, an introductory understanding of kotlin and knowing how to organize a project into data, view, and test layers.

### Aidan Shanley
- [Aidan S. Pull Request](https://github.com/CST438-11/Project1CST438/pulls/AShanleyCSUMB)
- [Aidan S. Issues](https://github.com/CST438-11/Project1CST438/issues?q=is%3Aissue%20state%3Aopen%20assignee%3AAShanleyCSUMB)

#### What was your role / which stories did you work on
I worked on the login, signup, and profile pages associated with users who use the site.

+ What was the biggest challenge? 
  + After initial drafts of some of pages are complete, I saw the biggest to challenge to be syncing up my machine's changes with the public repository.
+ Why was it a challenge?
  + Though programs that support GitHub allow for cloning repositories, they don't automatically save such changes back to GitHub.
+ How was the challenge addressed?
  + I copied and pasted each changed file, placing them into folders similar to my machine's setup.
+ Favorite / most interesting part of this project
  + For me, it's most interesting to see my work and all my team's work shown together as part of the final result.
+ If you could do it over, what would you change?
  + I would try to communicate more easily and more frequently with my team in case they suggest any changes as stuff gets sent in.
+ What is the most valuable thing you learned?
  + Team collaboration is essential for a successful project.


## Conclusion

- The project is pretty successful in our opinion because we were able to make the database to work as well as the API
- The largest victory is getting everything to work together, and getting the main priorities done such as creating database, creating the layout, and the API working
- Overall, the project was successful, but we would have liked to add more features to the app, such as allowing users to input an amount of money and convert it into the currency of their choice.
