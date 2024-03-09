<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['password'])){
    $jsonData = json_decode(file_get_contents('user.json'), true);
    foreach ($jsonData as $user){
        if($_POST['username'] == $user['username']){
            echo json_encode(array('success'=> false, 'message'=>'User already exists'));
            exit;
        }
    }
    $jsonData[] = [
        "username" => $_POST['username'],
        "password" => $_POST['password'],
        "userType" => "User",
        "notifications"=>[],
        "reminders"=>[]
    ];
    file_put_contents('user.json', json_encode($jsonData, JSON_PRETTY_PRINT));
    echo json_encode(array('success'=> true, 'message'=> 'User Registered'));
}else{
    echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/register.php
 * method: 'POST'
 * parameters: {username, password}
 */