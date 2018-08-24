<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class UsuarioAgregaCancion extends Model
{
    //
    protected $table="usuario_agrega_cancion";
    protected $fillable=['valoracion', 'idUsuario','idCancion','idListaReproduccion'];
    public $timestamps = false;

    public function cancion(){
        return $this->belongsTo('App\Cancion', 'idCancion');
    }

    public function usuario(){
        return $this->belongsTo('App\Usuario', 'idUsuario');
    }

    public function scopeSearch($query, $id){
        return $query->where('idUsuario', 'LIKE', "%$id%");

    }

    public static function searchPorUsuario($id){
        return self::query()->where('idListaReproduccion', 'LIKE', "%$id%");
    }
}
