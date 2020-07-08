package tk.mybatis.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import org.dozer.loader.api.TypeMappingOptions.mapEmptyString;

public class VoPoConverter {

    //    private static Mapper dozer = DozerBeanMapperBuilder.buildDefault();
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 将src对象中的属性值复制到desc对象的同名属性中
     *
     * @param src  源
     * @param desc 目标
     */
    public static void copyProperties(Object src, Object desc) {
        dozer.map(src, desc);
    }

    /**
     * 将src对象中的属性值复制到新建的desc对象的同名属性中
     *
     * @param src       源
     * @param descClass 目标class
     * @return
     */
    public static <T> T copyProperties(Object src, Class<T> descClass) {
        if (src == null)
            return null;
        return (T) dozer.map(src, descClass);
    }

    /**
     * 将srcList中的元素复制到元素类型为descClass的List集合中
     *
     * @param srcList   源list
     * @param descClass 目标list
     * @return
     */
    public static <T> List<T> copyList(@SuppressWarnings("rawtypes") List srcList, Class<T> descClass) {
        List<T> descList = new ArrayList<T>();
        if (srcList != null) {
            for (Object obj : srcList) {
                T t = VoPoConverter.copyProperties(obj, descClass);
                descList.add(t);
            }
        }
        return descList;
    }


    /**
     * 拷贝对象的时候可以忽略一些字段
     * Exception the Entity and Collection Type
     *  @param  dest 目标
     *  @param  orig 源
     *  @param  ignores 例如:vo.setUserName copy po.setUserName,应该写UserName
     *  @return  the dest bean
     */
    public static Object copyProperties(Object orig, Object dest, String[] ignores)   {
        if  (dest  ==   null   ||  orig  ==   null )   {
            return  dest;
        }
        PropertyDescriptor[] destDesc  =  PropertyUtils.getPropertyDescriptors(dest);
        try    {
            for  ( int  i  =   0 ; i  <  destDesc.length; i ++ )   {
                if  (contains(ignores, destDesc[i].getName()))   {
                    continue ;
                }
                Class destType  =  destDesc[i].getPropertyType();
                Class origType  =  PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                if (destType  !=   null   &&  destType.equals(origType)
                        &&   ! destType.equals(Class. class ))   {
                    if ( ! Collection. class .isAssignableFrom(origType))  {
                        Object value  =  PropertyUtils.getProperty(orig, destDesc[i].getName());
                        PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                    }
                }
            }
        } catch (Exception ex)   {
            ex.printStackTrace();
        }
        return  dest;
    }

    static   boolean  contains(String[] ignores, String name)   {
        boolean  ignored  =   false ;
        for  ( int  j  =   0 ; ignores  !=   null   &&  j  <  ignores.length; j ++ )   {
            if  (ignores[j].equals(name))   {
                ignored  =   true ;
                break ;
            }
        }
        return  ignored;
    }

    /**
     * list的深拷贝，原理是通过流的方式转换
     * @param src 转换的list参数
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static   List<?> listDeepCopy(List src) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(src);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        List<?> dest = (List<?>)inputStream.readObject();

        if (outputStream!=null) {
            outputStream.close();
        }
        if (inputStream!=null) {
            inputStream.close();
        }
        return dest;
    }

    /**
     * list的类型转换，通过json的方式
     * @param src 转换的list参数
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T>  List<T> listConver(List src,Class clazz) {
        List<T> list=new ArrayList<>();
        list=JSONArray.parseArray(JSON.toJSONString(src),clazz);
        return list;
    }


    /**
     * @param sources     源
     * @param destination 目标
     * @Description: copy对象方法   ，当属性值是null的时候 不赋值，适用于对象修改。
     * @version: v1.0.0
     * @author: liangpeng
     * @date: 2019年7月18日 下午3:15:58
     */
    public static void copyPropertiesNotNull(final Object sources, final Object destination) {

        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();

        mapper.addMapping(new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(sources.getClass(), destination.getClass(), TypeMappingOptions.mapNull(false), TypeMappingOptions.mapEmptyString(false));
            }
        });
        mapper.map(sources, destination);
        mapper.destroy();
        weakReference.clear();
    }
}
