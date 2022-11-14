package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// repository is used to store things-- repository interface (similar to set, collection, list, map interfaces we looked up in java doc), list  that will let us persist things into our database

// the @Repository annotation makes CustomerRepository a component when app starts
@Repository
//makes this a component when app starts so the class can implement the repository interface and use javadoc CRUD operations methods
// takes 2 types:Integer comes from Customer class-must  be the corresponding primary key
// JPARepository is a child of crudRepository, paging, query & repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    //    paging means seperate all results into pages--if searching for docs, only bring results in 20 pages using findall
    //    if you want to filter by anything, create your method here in the repository then add to controller
}
