<?php

namespace App\Http\Controllers;

use App\Genero;
use Illuminate\Http\Request;

class GeneroController extends Controller
{
    //
    public function guardar($genero){
        $generoNuevo = new Genero();
        $generoNuevo->genero = $genero;
        $generoNuevo->save();


    }
}
