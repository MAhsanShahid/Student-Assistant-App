package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

/**
 * Created by TALHA on 5.5.16.
 */
public class Event {
    private String Title;
    private String Priority;
    private String DueDate;
    private String ownerId;
    private java.util.Date created;
    private String objectId;
    private String Decsciption;
    private java.util.Date updated;
    public String getTitle()
    {
        return Title;
    }

    public void setTitle( String Title )
    {
        this.Title = Title;
    }

    public String getPriority()
    {
        return Priority;
    }

    public void setPriority( String Priority )
    {
        this.Priority = Priority;
    }

    public String getDueDate()
    {
        return DueDate;
    }

    public void setDueDate( String DueDate )
    {
        this.DueDate = DueDate;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public java.util.Date getCreated()
    {
        return created;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getDecsciption()
    {
        return Decsciption;
    }

    public void setDecsciption( String Decsciption )
    {
        this.Decsciption = Decsciption;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }


    public Event save()
    {
        return Backendless.Data.of( Event.class ).save( this );
    }

    public Future<Event> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Event> future = new Future<Event>();
            Backendless.Data.of( Event.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<Event> callback )
    {
        Backendless.Data.of( Event.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Event.class ).remove( this );
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
            Backendless.Data.of( Event.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Event.class ).remove( this, callback );
    }

    public static Event findById( String id )
    {
        return Backendless.Data.of( Event.class ).findById( id );
    }

    public static Future<Event> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Event> future = new Future<Event>();
            Backendless.Data.of( Event.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<Event> callback )
    {
        Backendless.Data.of( Event.class ).findById( id, callback );
    }

    public static Event findFirst()
    {
        return Backendless.Data.of( Event.class ).findFirst();
    }

    public static Future<Event> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Event> future = new Future<Event>();
            Backendless.Data.of( Event.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<Event> callback )
    {
        Backendless.Data.of( Event.class ).findFirst( callback );
    }

    public static Event findLast()
    {
        return Backendless.Data.of( Event.class ).findLast();
    }

    public static Future<Event> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Event> future = new Future<Event>();
            Backendless.Data.of( Event.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<Event> callback )
    {
        Backendless.Data.of( Event.class ).findLast( callback );
    }

    public static BackendlessCollection<Event> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( Event.class ).find( query );
    }

    public static Future<BackendlessCollection<Event>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<Event>> future = new Future<BackendlessCollection<Event>>();
            Backendless.Data.of( Event.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Event>> callback )
    {
        Backendless.Data.of( Event.class ).find( query, callback );
    }
}
