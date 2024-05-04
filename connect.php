<?php
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
    $birthDate=$_POST['birthDate'];
    $userType = $_POST['userType'];
    $sql2 ="Select * from tbluseraccount where username='".$uname."'";
    $result = mysqli_query($connection,$sql2);
    $numUsername = mysqli_num_rows($result);

    if($numUsername > 0)
        $message = "User name already exists";
    else if(empty($birthDate))
        $message = "Birth Date is required";
    else if(empty($pword))
        $message = "Password is required";
    else if(empty($email))
        $message = "Email is required";
    else if(empty($uname))
        $message = "Username is required";
    else{
        $sql1 ="Insert into tbluserprofile(firstname,lastname,gender, birthdate) 
                values('".$fname."','".$lname."','".$gender."', '".$birthDate."')";
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
            else if($row['usertype'] === 'admin')
                echo "<script>window.location.href = 'admin.php?uniqueid=".$row['acctid']."'</script>";
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
    else if(empty($_POST['deadlineJob']))
        $message = "Please enter deadline";
    else{
        $jobTitle = mysqli_real_escape_string($connection, $_POST['jobTitle']);
        $skills = mysqli_real_escape_string($connection, $_POST['skills']);
        $description = mysqli_real_escape_string($connection, $_POST['description']);
        $skills = mysqli_real_escape_string($connection, $_POST['skills']);
        $budget = intval(mysqli_real_escape_string($connection, $_POST['budget']));
        $deadlineJob = mysqli_real_escape_string($connection, $_POST['deadlineJob']);
        mysqli_query($connection, "INSERT INTO tbljobposting (employerID, employeeID, jobTitle, description, 
                           skillsRequired, budget, jobstatus, deadline) VALUES ('$acctid', '', '$jobTitle', '$description',
                                                                      '$skills', '$budget', 'Hiring', '".$deadlineJob."')");
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
    echo "<script>
            $('#modalMessage').get(0).textContent = 'Apply Job Successfully';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";
}

if(isset($_POST['deletePost'])){
    mysqli_query($connection, "DELETE FROM tbljobposting WHERE jobID='".$_POST['jobid']."'");
    echo "<script>
            $('#modalMessage').get(0).textContent = 'Delete Job Successfully';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";
}

if(isset($_POST['resign'])){
    mysqli_query($connection, "UPDATE tbljobposting SET jobstatus='Hiring', employeeID=0 WHERE jobID='".$_POST['jobid']."'");
    echo "<script>
            $('#modalMessage').get(0).textContent = 'Resign Job Successfully';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";
}

if(isset($_POST['removeEmployee'])){
    $statement = mysqli_prepare($connection,
        "UPDATE tbljobposting SET employeeID = 0, jobstatus='Hiring' where jobID = ?");
    mysqli_stmt_bind_param($statement, "i", $_POST['jobid']);
    mysqli_stmt_execute($statement);
    echo "<script>
            $('#modalMessage').get(0).textContent = 'Remove Employee Successfully';
            const myModalAlternative = new bootstrap.Modal('#modalAlreadyExists', null);
            myModalAlternative.show(myModalAlternative);
						</script>";
}

function getPostsEmployer(): void
{
    global $connection;
    $query = "SELECT * FROM tbljobposting";
    $result = mysqli_query($connection, $query);
    if ($result) {
        $data = getHeader($result);
        foreach ($data as $row) {
            tableRow($row, $connection);
            if($row['employerID'] == $_GET['uniqueid']){
                echo "
                    <td>    
                    <form method='post'>
                        <input type='hidden' name='jobid' value='".$row['jobID']."'>  
                        <button type='submit' name='deletePost'>Delete</button>
                    </form>
                    </td>
                    
                ";
                if($row['employeeID'] != 0){
                    echo "
                    <td>
                    <form method='post'>
                        <input type='hidden' name='jobid' value='".$row['jobID']."'>
                        <button type='submit' name='removeEmployee'>Remove Employee</button>
</form>
</td>";
                }
            }
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "Error: " . mysqli_error($connection);
    }
}

function getPostsEmployee(): void
{
    global $connection;
    $query = "SELECT * FROM tbljobposting";
    $result = mysqli_query($connection, $query);
    if ($result) {
        $data = getHeader($result);
        echo "</tr>";
        foreach ($data as $row) {
            tableRow($row, $connection);
            if ($row['jobstatus'] === 'Hiring') {
                echo "<td>
                        <form method='post'>
                            <input type='hidden' name='jobid' value=".$row['jobID'].">
                        <button type='submit' name='applyBtn'>Apply</button>
                        </form>
                        </td>";
            } else if ($row['employeeID'] === $_GET['uniqueid']) {
                echo "<td>
                    <form method='post'>
                    <input type='hidden' name='jobid' value=".$row['jobID'].">
                    <button type='submit' name='resign'>Resign</button>
                    </form>
  </td>";
            }

            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "Error: " . mysqli_error($connection);
    }
}

/**
 * @param mixed $row
 * @param mysqli $connection
 * @return void
 */
function tableRow(mixed $row, mysqli $connection): void
{
    echo "<tr>";
    foreach ($row as $key => $value) {
        if($key == 'jobID')
            continue;
        if($key == 'employeeID' && $value == 0){
            echo "<td> </td>";
        }else if (($key == 'employerID' || $key == 'employeeID') && $value != 0) {
            $result = mysqli_query($connection,
                "SELECT username FROM tbluseraccount WHERE acctid='" . $value . "'");
            echo "<td>'" . $result->fetch_assoc()['username'] . "'</td>";
        } else
            echo "<td>$value</td>";
    }
}

function getHeader($result): array
{
    $data = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    echo "<table>";
    echo "<tr>";
    foreach ($data[0] as $key => $value) {
        if($key == 'jobID')
            continue;
        if($key == 'employerID')
            echo "<th>employer</th>";
        else if($key == 'employeeID')
            echo "<th>employee</th>";
        else
            echo "<th>$key</th>";
    }
    echo "</tr>";
    return $data;
}


function getEmployeesInfo(): void
{
    global $connection;
    $result = mysqli_query($connection, "SELECT emailadd, username FROM tbluseraccount WHERE usertype='employee'");
    toTable($result);
}

/**
 * @param mysqli_result|bool $result
 * @return void
 */
function toTable(mysqli_result|bool $result): void
{
    $data = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    echo "<table>";
    echo "<tr>";
    foreach ($data[0] as $key => $value) {
        echo "<th>" . $key . "</th>";
    }
    echo "</tr>";
    foreach ($data as $row) {
        echo "<tr>";
        foreach ($row as $key => $value) {
            echo "<td>" . $value . "</td>";
        }
        echo "</tr>";
    }
    echo "</table>";
}

function getEmployersInfo(): void
{
    global $connection;
    $result = mysqli_query($connection, "SELECT emailadd, username FROM tbluseraccount WHERE usertype='employer'");
    if($result->num_rows > 0){
        toTable($result);
    }
}

function ongoingEJobs(): void
{
    global $connection;
    $result = mysqli_query($connection, "SELECT jobTitle FROM tbljobposting WHERE jobstatus='Ongoing'");
    if($result->num_rows > 0){
        toTable($result);
    }
}

function hiringJobs(): void
{
    global $connection;
    $result = mysqli_query($connection, "SELECT jobTitle FROM tbljobposting WHERE jobstatus='Hiring'");
    if($result->num_rows > 0){
        toTable($result);
    }
}