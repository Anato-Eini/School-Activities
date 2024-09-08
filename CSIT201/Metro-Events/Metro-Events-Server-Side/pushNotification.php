<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['message'], $_POST['notificationType'], $_POST['targetUsername'], $_POST['eventName'])){
    $userJSON = json_decode(file_get_contents('user.json'), true);
    foreach($userJSON as &$user){
        if($user['username'] == $_POST['username']){
            foreach ($user['notifications'] as $notification){
                if($notification['notificationName'] == $_POST['message']){
                    if($_POST['notificationType'] == 2){
                        echo json_encode(array("success" => false, "message" => "User already requested to join the event"));
                    }else if($_POST['notificationType'] == 1)
                        echo json_encode(array('success' => false, 'message' => "User already requested to be an Organizer"));
                    else if($_POST['notificationType'] == 5)
                        echo json_encode(array('success' => false, 'message' => "User already requested to be an Administrator"));
                    exit;
                }
            }
        $user['notifications'][] = [
            "targetUsername" => $_POST['targetUsername'],
            "eventName" => $_POST['eventName'],
            "notificationName" => $_POST['message'],
            "notificationType"=> $_POST['notificationType']
        ];
        file_put_contents('user.json', json_encode($userJSON, JSON_PRETTY_PRINT));
        if($_POST['notificationType'] == 2){
            echo json_encode(array("success" => true, "message" => "Notification has sent to the Organizer"));
        } else if($_POST['notificationType'] == 1 || $_POST['notificationType'] == 5)
            echo json_encode(array("success" => true, "message" => "Notification has sent to the Administrator"));
        else if ($_POST['notificationType'] == 3 || $_POST['notificationType'] == 4)
            echo json_encode(array("success" => true, "message" => "Success"));
        exit;
        }
    }
    echo json_encode(array(
        "success" => false,
        "message" => "User does not exist"
    ));
}else{
    echo json_encode(array(
        "success"=>false,
        "message"=> "Parameters not provided"
    ));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/pushNotification.php
 * method: 'POST'
 * parameters: {username, message, notificationType, targetUsername, eventName}
 *
 *
 * notificationType:
 * 1 = User requesting to be an Organizer to the Administrator
 * 2 = User requesting to join an event to the Organizer
 * 3 = Request to join event accepted
 * 4 = Request to join event declined
 * 5 = User/Organizer requesting to be an Administrator
 *
 */