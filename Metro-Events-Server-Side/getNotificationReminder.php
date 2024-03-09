<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_GET['username'])){
    $users = json_decode(file_get_contents('user.json'), true);
    foreach ($users as $user){
        if($user['username'] == $_GET['username']){
            echo json_encode(array('notifications'=>json_encode($user['notifications']), 'reminders'=>json_encode($user['reminders'])));
            exit;
        }
    }
}else echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/getNotificationReminder.php
 * method: GET
 * parameter: username
 */