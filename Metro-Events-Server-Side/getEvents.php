<?php
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
    header("Access-Control-Allow-Headers: Content-Type");
    if (isset($_GET['page'])) {
        if (!file_exists('events.json')) {
            echo json_encode(array(
                'success' => false,
                'message' => 'File Not Exist'
            ));
        } else {
            $events = file_get_contents('events.json');
            $inputNumber = $_GET['page'];
            $eventsDecoded = json_decode($events, true);
            echo json_encode(processPage($inputNumber, $eventsDecoded));
        }
    } else {
        $response = array(
            'success' => false,
            'message' => 'Page not provided'
        );
        echo json_encode($response);
    }
    function processPage($number, $arrayOfEvents)
    {
        $array = [];
        $starter = ((count($arrayOfEvents) - ($number * 20)) - 1);
        if ($starter < 0) {
            $starter = 0;
        }
        $ender = $number * 20 - 1;
        if ($ender > count($arrayOfEvents)) {
            $ender = count($arrayOfEvents) - 1;
        }
        for ($i = $starter; $i <= $ender; $i++) {
            $array[] = $arrayOfEvents[$i];
        }
        return $array;
    }
    /*
     * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/getEvents.php
     * method: 'GET'
     * parameters: {page}
     */