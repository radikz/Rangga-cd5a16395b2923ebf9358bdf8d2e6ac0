<?php
require("koneksi.php");
$perintah = "SELECT * FROM User";
$eksekusi = mysqli_query($konek, $perintah);
$cek = mysqli_affected_rows($konek);

if($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F["username"] = $ambil->username;
        $F["password"] = $ambil->password;
        $F["login_time"] = $ambil->login_time;
        $F["login_state"] = $ambil->login_state;
        
        array_push($response["data"], $F);
    }
    
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($konek);
