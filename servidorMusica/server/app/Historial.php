<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Historial extends Model
{
    //
    protected $table="historial";
    protected $fillable=['idUsuario', 'isCancion','fecha'];
    public $timestamps = false;

    public function cancion(){
        return $this->belongsTo('App\Cancion', 'idCancion');
    }
    public function scopeSearch($query, $id){
        return $query->where('idUsuario', 'LIKE', "%$id%");

    }
}

