<?php

namespace App\Http\Controllers;

use App\Cancion;
use Illuminate\Http\Request;

class CancionGuardaController extends Controller
{
    //
    public function guardar($titulo, $formato, $idGenero, $path, $colaboradores, $idUsuarioSubioCancion, $idAlbum, $calidad ){
        $cancionNueva = new Cancion();
        $cancionNueva->titulo = $titulo;
        $cancionNueva->formato = $formato;
        $cancionNueva->idGenero = $idGenero;
        $cancionNueva->path = $path;
        $cancionNueva->colaboradores = $colaboradores;
        $cancionNueva->idUsuarioSubioCancion = $idUsuarioSubioCancion;
        $cancionNueva->idAlbum = $idAlbum;
        $cancionNueva->calidad = $calidad;
        $cancionNueva->save();

    }
    public function guardar2($cancion){
        $cancionNueva = new Cancion();
        $array = json_decode($cancion);
        $cancionNueva->titulo = $array->titulo;
        $cancionNueva->formato = $array->formato;
        $cancionNueva->idGenero = $array->idGenero;
        $cancionNueva->path = $array->path;
        $cancionNueva->colaboradores = $array->colaboradores;
        $cancionNueva->idUsuarioSubioCancion = $array->idUsuarioSubioCancion;
        $cancionNueva->idAlbum = $array->idAlbum;
        $cancionNueva->calidad = $array->calidad;
        $cancionNueva->save();

    }
    public function reproducir($nombre){
        $cancionRecuperada = Cancion::Search($nombre)->orderBy('id', 'DESC')->paginate(100);
        $cancionRecuperada->each(function ($cancionRecuperada){
            $cancionRecuperada->usuario;
            $cancionRecuperada->genero;
            $cancionRecuperada->album;
        });
        return $cancionRecuperada;
    }
}
