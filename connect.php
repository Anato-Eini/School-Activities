<?php
$connection = new mysqli('localhost', 'root', '', 'dbacabalf3');

if(!$connection){
    die (mysqli_error($connection));
}

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
    //save data to tbluserprofile
    $sql1 ="Insert into tbluserprofile(firstname,lastname,gender) values('".$fname."','".$lname."','".$gender."')";
    mysqli_query($connection,$sql1);
    //Check tbluseraccount if username is already existing. Save info if false. Prompt msg if true.
    $sql2 ="Select * from tbluseraccount where username='".$uname."'";
    $result = mysqli_query($connection,$sql2);
    $row = mysqli_num_rows($result);
    if($row == 0){
        $sql ="Insert into tbluseraccount(emailadd,username,password) values('".$email."','".$uname."','".$pword."')";
        mysqli_query($connection,$sql);
        echo "<script language='javascript'>
						alert('New record saved.');
</script>";
        //header("location: index.php");
    }else{
        echo "<script language='javascript'>
						alert('Username already existing');
</script>";
    }

}
?>