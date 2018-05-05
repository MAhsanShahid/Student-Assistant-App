package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

/**
 * Created by TALHA on 5.5.16.
 */
public class Swap
{
    private String UserID;
    private String current_course;
    private String student_id;
    private java.util.Date created;
    private java.util.Date updated;
    private String objectId;
    private String owner_id;
    private String ownerId;
    private String required_course;
    private String current_section;
    private String required_section;
    public String getUserID()
    {
        return UserID;
    }

    public void setUserID( String UserID )
    {
        this.UserID = UserID;
    }

    public String getCurrent_course()
    {
        return current_course;
    }

    public void setCurrent_course( String current_course )
    {
        this.current_course = current_course;
    }

    public String getStudent_id()
    {
        return student_id;
    }

    public void setStudent_id( String student_id )
    {
        this.student_id = student_id;
    }

    public java.util.Date getCreated()
    {
        return created;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getOwner_id()
    {
        return owner_id;
    }

    public void setOwner_id( String owner_id )
    {
        this.owner_id = owner_id;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getRequired_course()
    {
        return required_course;
    }

    public void setRequired_course( String required_course )
    {
        this.required_course = required_course;
    }

    public String getCurrent_section()
    {
        return current_section;
    }

    public void setCurrent_section( String current_section )
    {
        this.current_section = current_section;
    }

    public String getRequired_section()
    {
        return required_section;
    }

    public void setRequired_section( String required_section )
    {
        this.required_section = required_section;
    }


    public Swap save()
    {
        return Backendless.Data.of( Swap.class ).save( this );
    }

    public Future<Swap> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Swap> future = new Future<Swap>();
            Backendless.Data.of( Swap.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<Swap> callback )
    {
        Backendless.Data.of( Swap.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Swap.class ).remove( this );
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
            Backendless.Data.of( Swap.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Swap.class ).remove( this, callback );
    }

    public static Swap findById( String id )
    {
        return Backendless.Data.of( Swap.class ).findById( id );
    }

    public static Future<Swap> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Swap> future = new Future<Swap>();
            Backendless.Data.of( Swap.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<Swap> callback )
    {
        Backendless.Data.of( Swap.class ).findById( id, callback );
    }

    public static Swap findFirst()
    {
        return Backendless.Data.of( Swap.class ).findFirst();
    }

    public static Future<Swap> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Swap> future = new Future<Swap>();
            Backendless.Data.of( Swap.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<Swap> callback )
    {
        Backendless.Data.of( Swap.class ).findFirst( callback );
    }

    public static Swap findLast()
    {
        return Backendless.Data.of( Swap.class ).findLast();
    }

    public static Future<Swap> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Swap> future = new Future<Swap>();
            Backendless.Data.of( Swap.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<Swap> callback )
    {
        Backendless.Data.of( Swap.class ).findLast( callback );
    }

    public static BackendlessCollection<Swap> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( Swap.class ).find( query );
    }

    public static Future<BackendlessCollection<Swap>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<Swap>> future = new Future<BackendlessCollection<Swap>>();
            Backendless.Data.of( Swap.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Swap>> callback )
    {
        Backendless.Data.of( Swap.class ).find( query, callback );
    }
}