<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ListaReproduccion extends Model
{
    //
    protected $table="lista_reproduccion";
    protected $fillable=['idUsuario', 'nombreLista','descripcion'];
    public $timestamps = false;

    public function usuario(){
        return $this->belongsTo('App\Usuario');
    }

    public function scopeSearch($query, $id){
        return $query->where('idUsuario', 'LIKE', "%$id%");

    }
}
