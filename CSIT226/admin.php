<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Main Page</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="css/styles2.css" rel="stylesheet" />
</head>
<div class="modal" tabindex="-1" id="modalAlreadyExists">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Alert!</h5>
            </div>
            <div class="modal-body">
                <p id="modalMessage">Incorrect Credentials</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<h2>Employees</h2>
<?php
require 'connect.php';
getEmployeesInfo();
echo "<h2>Employers</h2>";
getEmployersInfo();
echo "<h2>Ongoing Jobs</h2>";
ongoingEJobs();
echo "<h2>Hiring Jobs</h2>";
hiringJobs();
echo "<h2>Archived Jobs</h2>";
archivedJobs();
echo "<table>
<tr>
<th>Number of Employees</th>
<th>Number of Employers</th>
<th>Number of Female Users</th>
<th>Number of Male Users</th>
<th>Number of Ongoing Jobs</th>
<th>Number of Hiring Jobs</th>
</tr>
<tr>
<td>". numEmployees()."</td>
<td>". numEmployers()."</td>
<td>". numFemale()."</td>
<td>". numMale()."</td>
<td>". numOngoingJobs()."</td>
<td>". numHiringJobs()."</td>
</tr>
</table>
<h2>Overview</h2>
<img src='assets/Overview.png' alt='Image Overview'><br>
";
?>
<button class="btn btn-primary btn-block" type="submit" name="logOut" onclick="window.location.href = 'index.php'">
    Log Out
</button>
