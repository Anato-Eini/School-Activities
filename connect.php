<?php
$connection = new mysqli('localhost', 'root', '', 'dbacabalf3');

if(!$connection->connect_error){
    error_log (mysqli_error($connection));
}

$message = '';
if(isset($_POST['btnRegister'])){
    //retrieve data from form and save the value to a variable
    //for tbluserprofile
    $fname=$_POST['txtfirstname'];
    $lname=$_POST['txtlastname'];
    $gender=$_POST['txtgender'];
    //for tbluseraccount
    $email=$_POST['txtemail'];
    $uname=$_POST['txtusername'];
    $pword=$_POST['txtpassword'];
    //Check tbluseraccount if username is already existing. Save info if false. Prompt msg if true.
    $sql2 ="Select * from tbluseraccount where username='".$uname."'";
    $result = mysqli_query($connection,$sql2);
    $numUsername = mysqli_num_rows($result);
    $numFirstName = mysqli_num_rows(mysqli_query($connection,
        "Select * from tbluserprofile where firstname='".$fname."'"));
    $numLastName = mysqli_num_rows(mysqli_query($connection,
        "Select * from tbluserprofile where lastname='".$lname."'"));
    $numPassword = mysqli_num_rows(mysqli_query($connection,
        "Select * from tbluseraccount where password='".password_hash($pword, PASSWORD_DEFAULT)."'"));
    $numEmailAdd = mysqli_num_rows(mysqli_query($connection,
        "Select * from tbluseraccount where emailadd='".$email."'"));
    if($numUsername > 0)
        $message = "User name already exists";
    else if($numFirstName > 0)
        $message = "First name already exists";
    else if($numLastName > 0)
        $message = "Password already exists";
    else if($numPassword > 0)
        $message = "Password already exists";
    else if($numEmailAdd > 0)
        $message = "Email Address already exists";
    else{
        $sql1 ="Insert into tbluserprofile(firstname,lastname,gender) 
                values('".$fname."','".$lname."','".$gender."')";
        mysqli_query($connection,$sql1);
        $sql ="Insert into tbluseraccount(emailadd,username,password, usertype) 
                values('".$email."','".$uname."','".password_hash($pword, PASSWORD_DEFAULT)."', 'employee')";
        mysqli_query($connection,$sql);
        $message = "Registered Successfully";
    }
    echo "<script>
                       $('#modalMessage').get(0).textContent = '".$message."';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";

}


if(isset($_GET['btnLogin'])){
    $username = $_GET['txtUsername'];
    $password = $_GET['txtPassword'];

    $sqlQuery = mysqli_query($connection,
        "Select password from tbluseraccount where username='".$username."'");
    if($sqlQuery) {
        $row = mysqli_fetch_assoc($sqlQuery);

        if(password_verify($password, $row['password'])) {
            echo "<script>
            window.location.href = 'mainPage.php'</script>";
            exit();
        }else{
            echo "<script>
            $('.modalMessage').get(0).textContent = 'Wrong Password database ".$row['password']."';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
            </script>";
        }
    }else{
        echo "<script>
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
            </script>";
    }
}