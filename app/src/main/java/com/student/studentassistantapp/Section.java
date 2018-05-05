package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

/**
 * Created by TALHA on 10.4.16.
 */
public class Section
{
    private String objectId;
    private String ownerId;
    private String SectionName;
    private java.util.Date updated;
    private Integer SectionID;
    private java.util.Date created;
    public String getObjectId()
    {
        return objectId;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getSectionName()
    {
        return SectionName;
    }

    public void setSectionName( String SectionName )
    {
        this.SectionName = SectionName;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }

    public Integer getSectionID()
    {
        return SectionID;
    }

    public void setSectionID( Integer SectionID )
    {
        this.SectionID = SectionID;
    }

    public java.util.Date getCreated()
    {
        return created;
    }


    public Section save()
    {
        return Backendless.Data.of( Section.class ).save( this );
    }

    public Future<Section> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Section> future = new Future<Section>();
            Backendless.Data.of( Section.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<Section> callback )
    {
        Backendless.Data.of( Section.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Section.class ).remove( this );
    }

    public Future<Long> removeAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of( Section.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Section.class ).remove( this, callback );
    }

    public static Section findById( String id )
    {
        return Backendless.Data.of( Section.class ).findById( id );
    }

    public static Future<Section> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Section> future = new Future<Section>();
            Backendless.Data.of( Section.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<Section> callback )
    {
        Backendless.Data.of( Section.class ).findById( id, callback );
    }

    public static Section findFirst()
    {
        return Backendless.Data.of( Section.class ).findFirst();
    }

    public static Future<Section> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Section> future = new Future<Section>();
            Backendless.Data.of( Section.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<Section> callback )
    {
        Backendless.Data.of( Section.class ).findFirst( callback );
    }

    public static Section findLast()
    {
        return Backendless.Data.of( Section.class ).findLast();
    }

    public static Future<Section> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Section> future = new Future<Section>();
            Backendless.Data.of( Section.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<Section> callback )
    {
        Backendless.Data.of( Section.class ).findLast( callback );
    }

    public static BackendlessCollection<Section> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( Section.class ).find( query );
    }

    public static Future<BackendlessCollection<Section>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<Section>> future = new Future<BackendlessCollection<Section>>();
            Backendless.Data.of( Section.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Section>> callback )
    {
        Backendless.Data.of( Section.class ).find( query, callback );
    }
}