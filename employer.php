
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Register</title>
    <link href="css/styles2.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<!--<form method="post">

    <div class="col-md-6">
        <div class="form-floating mb-3 mb-md-0">
            <input class="form-control" id="jobTitle" type="text" placeholder="Enter the job title" name="jobTitle"/>
            <label for="jobTitle">Enter the Job title</label>
        </div>
    </div>
    <button class="btn btn-primary btn-block" type="submit" name="btnPostJob">
        Post Job
    </button>
</form>-->

<?php
require 'connect.php';
getPosts();
?>