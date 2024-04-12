<?php
session_start();
$connection = new mysqli('localhost', 'root', '', 'dbacabalf3');

if(!$connection->connect_error){
    error_log (mysqli_error($connection));
}

$acctid = null;

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
    $userType = $_POST['userType'];
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
                values('".$email."','".$uname."','".password_hash($pword, PASSWORD_DEFAULT)."', '". $userType."')";
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
        "SELECT * FROM tbluseraccount WHERE username='".$username."'");
    if($sqlQuery) {
        $row = mysqli_fetch_assoc($sqlQuery);
        if(password_verify($password, $row['password'])) {
            if($row['usertype'] === 'employer')
                echo "<script>window.location.href = 'employer.php?uniqueid=".$row['acctid']."'</script>";
            else if($row['usertype'] === 'employee')
                echo "<script>window.location.href = 'employee.php?uniqueid=".$row['acctid']."'</script>";
        } else {
            echo "<script>
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
            </script>";
        }
    } else {
        echo "<script>
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
            </script>";
    }
}

if(isset($_GET['uniqueid'])){
    global $acctid;
    $acctid = $_GET['uniqueid'];
}

if(isset($_POST['btnPostJob'])){
    if(empty($_POST['jobTitle']))
        $message = "Please enter a job title";
    else if(empty($_POST['description']))
        $message = "Please enter a job description";
    else if(empty($_POST['skills']))
        $message = "Please enter skills";
    else if(empty($_POST['budget']))
        $message = "Please enter budget";
    else {
        $jobTitle = mysqli_real_escape_string($connection, $_POST['jobTitle']);
        $skills = mysqli_real_escape_string($connection, $_POST['skills']);
        $description = mysqli_real_escape_string($connection, $_POST['description']);
        $skills = mysqli_real_escape_string($connection, $_POST['skills']);
        $budget = intval(mysqli_real_escape_string($connection, $_POST['budget']));
        mysqli_query($connection, "INSERT INTO tbljobposting (employerID, employeeID, jobTitle, description, 
                           skillsRequired, budget, jobstatus) VALUES ('$acctid', '', '$jobTitle', '$description',
                                                                      '$skills', '$budget', 'Hiring')");
        $message = "Job Posted Successfully";
    }
    echo "<script>
            $('#modalMessage').get(0).textContent = '".$message."';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";
}

if(isset($_POST['applyBtn'])){
    $jobid = $_POST['jobid'];
    mysqli_query($connection, "UPDATE tbljobposting SET employeeID='".$_GET['uniqueid']."',jobstatus='Ongoing' 
    WHERE jobID='".$jobid."'");
}

function getPostsEmployee(): void
{
    global $connection;
    $query = "SELECT * FROM tbljobposting";
    $result = mysqli_query($connection, $query);
    if ($result) {
        $data = array();
        while ($row = mysqli_fetch_assoc($result)) {
            $data[] = $row;
        }

        echo "<table>";
        echo "<tr>";
        foreach ($data[0] as $key => $value) {
            echo "<th>$key</th>";
        }
        echo "</tr>";
        foreach ($data as $row) {
            echo "<tr>";
            foreach ($row as $value) {
                echo "<td>$value</td>";
            }
            if ($row['jobstatus'] === 'Hiring') {
                echo "<td>
                            <form method='post'>
                                <input type='hidden' name='jobid' value=".$row['jobID'].">
                            <button type='submit' name='applyBtn'>Apply</button>
                            </form>";
            } else {
                echo "<td></td>";
            }

            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "Error: " . mysqli_error($connection);
    }
}


function getPosts(): void
{
    global $connection;
    $query = "SELECT * FROM tbljobposting";
    $result = mysqli_query($connection, $query);
    if ($result) {
        $data = array();
        while ($row = mysqli_fetch_assoc($result)) {
            $data[] = $row;
        }

        echo "<table>";
        echo "<tr>";
        foreach ($data[0] as $key => $value) {
            echo "<th>$key</th>";
        }
        echo "</tr>";
        foreach ($data as $row) {
            echo "<tr>";
            foreach ($row as $value) {
                echo "<td>$value</td>";
            }
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "Error: " . mysqli_error($connection);
    }
}
