package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;


public class Course
{
    private java.util.Date created;
    private String objectId;
    private String CourseName;
    private java.util.Date updated;
    private Integer CreditHr;
    private String ownerId;
    private String CourseID;
    public java.util.Date getCreated()
    {
        return created;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getCourseName()
    {
        return CourseName;
    }

    public void setCourseName( String CourseName )
    {
        this.CourseName = CourseName;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }

    public Integer getCreditHr()
    {
        return CreditHr;
    }

    public void setCreditHr( Integer CreditHr )
    {
        this.CreditHr = CreditHr;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getCourseID()
    {
        return CourseID;
    }

    public void setCourseID( String CourseID )
    {
        this.CourseID = CourseID;
    }


    public Course save()
    {
        return Backendless.Data.of( Course.class ).save( this );
    }

    public Future<Course> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Course> future = new Future<Course>();
            Backendless.Data.of( Course.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<Course> callback )
    {
        Backendless.Data.of( Course.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Course.class ).remove( this );
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
            Backendless.Data.of( Course.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Course.class ).remove( this, callback );
    }

    public static Course findById( String id )
    {
        return Backendless.Data.of( Course.class ).findById( id );
    }

    public static Future<Course> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Course> future = new Future<Course>();
            Backendless.Data.of( Course.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<Course> callback )
    {
        Backendless.Data.of( Course.class ).findById( id, callback );
    }

    public static Course findFirst()
    {
        return Backendless.Data.of( Course.class ).findFirst();
    }

    public static Future<Course> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Course> future = new Future<Course>();
            Backendless.Data.of( Course.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<Course> callback )
    {
        Backendless.Data.of( Course.class ).findFirst( callback );
    }

    public static Course findLast()
    {
        return Backendless.Data.of( Course.class ).findLast();
    }

    public static Future<Course> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<Course> future = new Future<Course>();
            Backendless.Data.of( Course.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<Course> callback )
    {
        Backendless.Data.of( Course.class ).findLast( callback );
    }

    public static BackendlessCollection<Course> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( Course.class ).find( query );
    }

    public static Future<BackendlessCollection<Course>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<Course>> future = new Future<BackendlessCollection<Course>>();
            Backendless.Data.of( Course.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Course>> callback )
    {
        Backendless.Data.of( Course.class ).find( query, callback );
    }
}