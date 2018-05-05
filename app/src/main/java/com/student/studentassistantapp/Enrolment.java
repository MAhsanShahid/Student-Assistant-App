package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Enrolment
{
    private java.util.Date created;
    private String objectId;
    private String Student_id;
    private Double CreditHr_earned;
    private String Grade;
    private java.util.Date updated;
    private String ownerId;
    private String Class_name;
    private String Class_id;
    public java.util.Date getCreated()
    {
        return created;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getStudent_id()
    {
        return Student_id;
    }

    public void setStudent_id( String Student_id )
    {
        this.Student_id = Student_id;
    }

    public Double getCreditHr_earned()
    {
        return CreditHr_earned;
    }

    public void setCreditHr_earned( Double CreditHr_earned )
    {
        this.CreditHr_earned = CreditHr_earned;
    }

    public String getGrade()
    {
        return Grade;
    }

    public void setGrade( String Grade )
    {
        this.Grade = Grade;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getClass_name()
    {
        return Class_name;
    }

    public void setClass_name( String Class_name )
    {
        this.Class_name = Class_name;
    }

    public String getClass_id()
    {
        return Class_id;
    }

    public void setClass_id( String Class_id )
    {
        this.Class_id = Class_id;
    }


    public Enrolment save()
    {
        return Backendless.Data.of( Enrolment.class ).save( this );
    }

    public Future<Enrolment> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Enrolment> future = new Future<Enrolment>();
            Backendless.Data.of( Enrolment.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<Enrolment> callback )
    {
        Backendless.Data.of( Enrolment.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Enrolment.class ).remove( this );
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
            Backendless.Data.of( Enrolment.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Enrolment.class ).remove( this, callback );
    }

    public static Enrolment findById( String id )
    {
        return Backendless.Data.of( Enrolment.class ).findById( id );
    }

    public static Future<Enrolment> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Enrolment> future = new Future<Enrolment>();
            Backendless.Data.of( Enrolment.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<Enrolment> callback )
    {
        Backendless.Data.of( Enrolment.class ).findById( id, callback );
    }

    public static Enrolment findFirst()
    {
        return Backendless.Data.of( Enrolment.class ).findFirst();
    }

    public static Future<Enrolment> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Enrolment> future = new Future<Enrolment>();
            Backendless.Data.of( Enrolment.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<Enrolment> callback )
    {
        Backendless.Data.of( Enrolment.class ).findFirst( callback );
    }

    public static Enrolment findLast()
    {
        return Backendless.Data.of( Enrolment.class ).findLast();
    }

    public static Future<Enrolment> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Enrolment> future = new Future<Enrolment>();
            Backendless.Data.of( Enrolment.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<Enrolment> callback )
    {
        Backendless.Data.of( Enrolment.class ).findLast( callback );
    }

    public static BackendlessCollection<Enrolment> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( Enrolment.class ).find( query );
    }

    public static Future<BackendlessCollection<Enrolment>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<Enrolment>> future = new Future<BackendlessCollection<Enrolment>>();
            Backendless.Data.of( Enrolment.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Enrolment>> callback )
    {
        Backendless.Data.of( Enrolment.class ).find( query, callback );
    }
}