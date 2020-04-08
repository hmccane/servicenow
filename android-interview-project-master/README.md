# Servicenow-Android-Exercise
## Take-home project for Servicenow Android Candidates

### Requirements
- A computer setup for Android development (contact your recruiter if one is not available)

### Objectives:
- Have fun
- Demonstrate knowledge of Android development

#### Exercise Details

- You may use Java or Kotlin, both options are available to you.
- You are free to use any third party libraries. For your convenience, we have already added a few commonly used libraries in the [build.gradle](https://code.devsnc.com/terry-schmidt/Servicenow-Android-Exercise/blob/master/app/build.gradle).
- Please use git and commit often with meaningful commit messages, just as you would when working on a team.
- If you make an assumption during this exercise, run with it, but please write it down in a comment so we know the assumption when reviewing the code.
- You should fix any memory leaks, crashes, or bad practices you might find.
- Remember to think about common pitfalls such as how your app handles a configuration change.

You are given a simple project with a list of Coffee Shop Reviews. This list is loaded from locally stored data in a class called CoffeeShopReviews.
 
#### Exercise 1:

Replace the ListView with a RecyclerView. Upon tapping a recycler view cell, open a detail activity or fragment and show the review, rating, and location.

#### Exercise 2:

Use Retrofit or a similar networking library to load the review information from a web resource (https://jsonblob.com/api/jsonBlob/c1a89a37-371e-11ea-a549-6f3544633231) instead of using the local data. You may use Gson or another parsing library.

#### Extra Credit: 
- Improve the project in any way you see fit (UX/UI, code structure, etc) and explain your enhancements in comments.

### When you are done

Zip the project using the provided script in project root and email the zip file back to the recruiter.

`./zip-project.sh`
