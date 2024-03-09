<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['userType'])){
    $users = json_decode(file_get_contents('user.json'), true);
    foreach ($users as &$user) {
        if($user['username'] == $_POST && $user['userType'] == $_POST['userType']){
            echo json_encode(array('success'=>false, 'message'=>'User already a/n '.$_POST['userType']));
            exit;
        }else if($user['username'] == $_POST['username']) {
            $user['userType'] = $_POST['userType'];
            echo json_encode(array('success' => true, 'message' => 'Update user type successfully'));
            file_put_contents('user.json', json_encode($users, JSON_PRETTY_PRINT));
            exit;
        }
    }
}else echo json_encode(array('success'=> false, 'message' => "Parameters not provided"));
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/updateUserType.php
 * method: "POST"
 * parameters: username, userType
 */