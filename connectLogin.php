<?php
$connection = new mysqli('localhost', 'root', "", "dbacabalf3");
if(!$connection->connect_error){
    die (mysqli_error($connection));
}

//if(isset($_GET['']))