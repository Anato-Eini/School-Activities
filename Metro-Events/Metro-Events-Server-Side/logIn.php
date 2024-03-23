<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
$usersJSON = json_decode(file_get_contents('user.json'), true);
if(isset($_GET['username'], $_GET['password'])){
    foreach ($usersJSON as $user){
        if($user['username'] == $_GET['username'] && $user['password'] == $_GET['password']){
            $userType= null;
            if($user['userType'] == 'Administrator') $userType = 1;
            else if($user['userType'] == 'Organizer') $userType = 2;
            else $userType = 3;
            echo json_encode(array('success'=> true, 'message'=>"Login Successfully", 'type'=>$userType, 'notifications'=>
            json_encode($user['notifications']), 'reminders'=>json_encode($user['reminders'])));
            exit;
        }
    }
    echo json_encode(array('success' => false, 'message'=>'Incorrect Credentials'));
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/logIn.php
 * method: 'GET'
 * parameters: {username, password}
 */