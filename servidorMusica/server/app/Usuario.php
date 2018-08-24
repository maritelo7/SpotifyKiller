<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Usuario extends Model
{
    //
    protected $table="usuario";
    protected $fillable=['nombre', 'apellidoPat', 'apellidoMat', 'fechaNacimiento', 'clave', 'nombreArtistico', 'tipoUsuario', 'nombreUsuario', 'calidadDescarga', 'calidadStream'];
    public function cancion(){
        return $this->hasMany('App\Cancion');
    }
    public function album(){
        return $this->hasMany('App\Album');

    }
    public function listaReproduccion(){
        return $this->hasMany('App\ListaReproduccion');

    }
    public function historial(){
        return $this->hasMany('App\Historial');

    }

}
