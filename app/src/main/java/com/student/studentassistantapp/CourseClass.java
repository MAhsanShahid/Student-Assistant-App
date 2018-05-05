package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class CourseClass
{
    private String Teacher_id;
    private java.util.Date created;
    private java.util.Date updated;
    private String Course_id;
    private String Course_name;
    private String ownerId;
    private String objectId;
    private String Section_name;
    private Integer Section_id;
    public String getTeacher_id()
    {
        return Teacher_id;
    }

    public void setTeacher_id( String Teacher_id )
    {
        this.Teacher_id = Teacher_id;
    }

    public java.util.Date getCreated()
    {
        return created;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }

    public String getCourse_id()
    {
        return Course_id;
    }

    public void setCourse_id( String Course_id )
    {
        this.Course_id = Course_id;
    }

    public String getCourse_name()
    {
        return Course_name;
    }

    public void setCourse_name( String Course_name )
    {
        this.Course_name = Course_name;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getSection_name()
    {
        return Section_name;
    }

    public void setSection_name( String Section_name )
    {
        this.Section_name = Section_name;
    }

    public Integer getSection_id()
    {
        return Section_id;
    }

    public void setSection_id( Integer Section_id )
    {
        this.Section_id = Section_id;
    }


    public CourseClass save()
    {
        return Backendless.Data.of( CourseClass.class ).save( this );
    }

    public Future<CourseClass> saveAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<CourseClass> future = new Future<CourseClass>();
            Backendless.Data.of( CourseClass.class ).save( this, future );

            return future;
        }
    }

    public void saveAsync( AsyncCallback<CourseClass> callback )
    {
        Backendless.Data.of( CourseClass.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( CourseClass.class ).remove( this );
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
            Backendless.Data.of( CourseClass.class ).remove( this, future );

            return future;
        }
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( CourseClass.class ).remove( this, callback );
    }

    public static CourseClass findById( String id )
    {
        return Backendless.Data.of( CourseClass.class ).findById( id );
    }

    public static Future<CourseClass> findByIdAsync( String id )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<CourseClass> future = new Future<CourseClass>();
            Backendless.Data.of( CourseClass.class ).findById( id, future );

            return future;
        }
    }

    public static void findByIdAsync( String id, AsyncCallback<CourseClass> callback )
    {
        Backendless.Data.of( CourseClass.class ).findById( id, callback );
    }

    public static CourseClass findFirst()
    {
        return Backendless.Data.of( CourseClass.class ).findFirst();
    }

    public static Future<CourseClass> findFirstAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<CourseClass> future = new Future<CourseClass>();
            Backendless.Data.of( CourseClass.class ).findFirst( future );

            return future;
        }
    }

    public static void findFirstAsync( AsyncCallback<CourseClass> callback )
    {
        Backendless.Data.of( CourseClass.class ).findFirst( callback );
    }

    public static CourseClass findLast()
    {
        return Backendless.Data.of( CourseClass.class ).findLast();
    }

    public static Future<CourseClass> findLastAsync()
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<CourseClass> future = new Future<CourseClass>();
            Backendless.Data.of( CourseClass.class ).findLast( future );

            return future;
        }
    }

    public static void findLastAsync( AsyncCallback<CourseClass> callback )
    {
        Backendless.Data.of( CourseClass.class ).findLast( callback );
    }

    public static BackendlessCollection<CourseClass> find( BackendlessDataQuery query )
    {
        return Backendless.Data.of( CourseClass.class ).find( query );
    }

    public static Future<BackendlessCollection<CourseClass>> findAsync( BackendlessDataQuery query )
    {
        if( Backendless.isAndroid() )
        {
            throw new UnsupportedOperationException( "Using this method is restricted in Android" );
        }
        else
        {
            Future<BackendlessCollection<CourseClass>> future = new Future<BackendlessCollection<CourseClass>>();
            Backendless.Data.of( CourseClass.class ).find( query, future );

            return future;
        }
    }

    public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<CourseClass>> callback )
    {
        Backendless.Data.of( CourseClass.class ).find( query, callback );
    }
}