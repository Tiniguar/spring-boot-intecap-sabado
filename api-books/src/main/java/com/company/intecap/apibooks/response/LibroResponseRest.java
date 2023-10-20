package com.company.intecap.apibooks.response;

public class LibroResponseRest extends  ResponseRest{
    private LibroReponse libroReponse = new LibroReponse();

    public LibroReponse getLibroReponse() {
        return libroReponse;
    }

    public void setLibroReponse(LibroReponse libroReponse) {
        this.libroReponse = libroReponse;
    }
}
