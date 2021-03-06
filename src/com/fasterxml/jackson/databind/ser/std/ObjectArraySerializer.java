package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer
  extends ArraySerializerBase<Object[]>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicSerializers;
  protected JsonSerializer<Object> _elementSerializer;
  protected final JavaType _elementType;
  protected final boolean _staticTyping;
  protected final TypeSerializer _valueTypeSerializer;
  
  public ObjectArraySerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Object[].class);
    _elementType = paramJavaType;
    _staticTyping = paramBoolean;
    _valueTypeSerializer = paramTypeSerializer;
    _dynamicSerializers = PropertySerializerMap.emptyForProperties();
    _elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramObjectArraySerializer, paramBeanProperty, paramBoolean);
    _elementType = _elementType;
    _valueTypeSerializer = paramTypeSerializer;
    _staticTyping = _staticTyping;
    _dynamicSerializers = _dynamicSerializers;
    _elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, TypeSerializer paramTypeSerializer)
  {
    super(paramObjectArraySerializer);
    _elementType = _elementType;
    _valueTypeSerializer = paramTypeSerializer;
    _staticTyping = _staticTyping;
    _dynamicSerializers = _dynamicSerializers;
    _elementSerializer = _elementSerializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramJavaType = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicSerializers = map;
    }
    return serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramClass = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicSerializers = map;
    }
    return serializer;
  }
  
  public JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, Boolean paramBoolean)
  {
    return new ObjectArraySerializer(this, paramBeanProperty, _valueTypeSerializer, _elementSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new ObjectArraySerializer(_elementType, _staticTyping, paramTypeSerializer, _elementSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonArrayFormatVisitor localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
    if (localJsonArrayFormatVisitor != null)
    {
      JavaType localJavaType = paramJsonFormatVisitorWrapper.getProvider().getTypeFactory().moreSpecificType(_elementType, paramJavaType.getContentType());
      if (localJavaType == null) {
        throw new JsonMappingException("Could not resolve type");
      }
      JsonSerializer localJsonSerializer = _elementSerializer;
      paramJavaType = localJsonSerializer;
      if (localJsonSerializer == null) {
        paramJavaType = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType, _property);
      }
      localJsonArrayFormatVisitor.itemsFormat(paramJavaType, localJavaType);
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = _valueTypeSerializer;
    Object localObject3 = localObject1;
    if (localObject1 != null) {
      localObject3 = ((TypeSerializer)localObject1).forProperty(paramBeanProperty);
    }
    Object localObject2 = null;
    JsonFormat.Value localValue = null;
    Object localObject5 = null;
    Object localObject4 = localObject5;
    if (paramBeanProperty != null)
    {
      localObject4 = paramBeanProperty.getMember();
      localObject2 = paramSerializerProvider.getAnnotationIntrospector();
      localObject1 = localValue;
      if (localObject4 != null)
      {
        Object localObject6 = ((AnnotationIntrospector)localObject2).findContentSerializer((Annotated)localObject4);
        localObject1 = localValue;
        if (localObject6 != null) {
          localObject1 = paramSerializerProvider.serializerInstance((Annotated)localObject4, localObject6);
        }
      }
      localValue = paramBeanProperty.findFormatOverrides((AnnotationIntrospector)localObject2);
      localObject2 = localObject1;
      localObject4 = localObject5;
      if (localValue != null)
      {
        localObject4 = localValue.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        localObject2 = localObject1;
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = _elementSerializer;
    }
    localObject2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject1);
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (_elementType != null) {
        if (!_staticTyping)
        {
          localObject1 = localObject2;
          if (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty)) {
            break label185;
          }
        }
      }
    }
    for (localObject1 = paramSerializerProvider.findValueSerializer(_elementType, paramBeanProperty);; localObject1 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject2, paramBeanProperty)) {
      label185:
      return withResolved(paramBeanProperty, (TypeSerializer)localObject3, (JsonSerializer)localObject1, (Boolean)localObject4);
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return _elementSerializer;
  }
  
  public JavaType getContentType()
  {
    return _elementType;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode = createSchemaNode("array", true);
    if (paramType != null)
    {
      paramType = paramSerializerProvider.constructType(paramType);
      if (paramType.isArrayType())
      {
        paramType = ((ArrayType)paramType).getContentType().getRawClass();
        if (paramType != Object.class) {
          break label54;
        }
        localObjectNode.set("items", JsonSchema.getDefaultSchemaNode());
      }
    }
    return localObjectNode;
    label54:
    paramType = paramSerializerProvider.findValueSerializer(paramType, _property);
    if ((paramType instanceof SchemaAware)) {}
    for (paramSerializerProvider = ((SchemaAware)paramType).getSchema(paramSerializerProvider, null);; paramSerializerProvider = JsonSchema.getDefaultSchemaNode())
    {
      localObjectNode.set("items", paramSerializerProvider);
      return localObjectNode;
    }
  }
  
  public boolean hasSingleElement(Object[] paramArrayOfObject)
  {
    return paramArrayOfObject.length == 1;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Object[] paramArrayOfObject)
  {
    return (paramArrayOfObject == null) || (paramArrayOfObject.length == 0);
  }
  
  public final void serialize(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfObject.length;
    if ((i == 1) && (((_unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (_unwrapSingle == Boolean.TRUE)))
    {
      serializeContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray(i);
    serializeContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int k = paramArrayOfObject.length;
    if (k == 0) {
      return;
    }
    if (_elementSerializer != null)
    {
      serializeContentsUsing(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider, _elementSerializer);
      return;
    }
    if (_valueTypeSerializer != null)
    {
      serializeTypedContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    int j = 0;
    int i = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localPropertySerializerMap = _dynamicSerializers;
        if (i >= k) {
          break;
        }
        localObject2 = paramArrayOfObject[i];
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break label268;
        }
        localObject1 = localObject2;
        j = i;
        localClass = localObject2.getClass();
        localObject1 = localObject2;
        j = i;
        JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null)
        {
          localObject1 = localObject2;
          j = i;
          if (!_elementType.hasGenericTypes()) {
            continue;
          }
          localObject1 = localObject2;
          j = i;
          localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(_elementType, localClass), paramSerializerProvider);
        }
      }
      catch (IOException paramArrayOfObject)
      {
        PropertySerializerMap localPropertySerializerMap;
        Object localObject2;
        Class localClass;
        throw paramArrayOfObject;
        localObject1 = localObject2;
        j = i;
        JsonSerializer localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        continue;
      }
      catch (Exception paramArrayOfObject)
      {
        if ((!(paramArrayOfObject instanceof InvocationTargetException)) || (paramArrayOfObject.getCause() == null)) {
          continue;
        }
        paramArrayOfObject = paramArrayOfObject.getCause();
        continue;
        if (!(paramArrayOfObject instanceof Error)) {
          continue;
        }
        throw ((Error)paramArrayOfObject);
        throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject1, j);
      }
      localObject1 = localObject2;
      j = i;
      localJsonSerializer1.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      label268:
      i += 1;
    }
  }
  
  public void serializeContentsUsing(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    int j = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i < j)
      {
        localObject = paramArrayOfObject[i];
        if (localObject == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (IOException paramArrayOfObject)
        {
          throw paramArrayOfObject;
          paramJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
        catch (Exception paramArrayOfObject)
        {
          while (((paramArrayOfObject instanceof InvocationTargetException)) && (paramArrayOfObject.getCause() != null)) {
            paramArrayOfObject = paramArrayOfObject.getCause();
          }
          if (!(paramArrayOfObject instanceof Error)) {
            break label108;
          }
          throw ((Error)paramArrayOfObject);
          throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject, i);
        }
        if (localTypeSerializer == null)
        {
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
          break label118;
        }
      }
      label108:
      return;
      label118:
      i += 1;
    }
  }
  
  public void serializeTypedContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int k = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int j = 0;
    int i = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        PropertySerializerMap localPropertySerializerMap = _dynamicSerializers;
        if (i < k)
        {
          Object localObject2 = paramArrayOfObject[i];
          if (localObject2 == null)
          {
            localObject1 = localObject2;
            j = i;
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            localObject1 = localObject2;
            j = i;
            Class localClass = localObject2.getClass();
            localObject1 = localObject2;
            j = i;
            JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
            JsonSerializer localJsonSerializer1 = localJsonSerializer2;
            if (localJsonSerializer2 == null)
            {
              localObject1 = localObject2;
              j = i;
              localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
            }
            localObject1 = localObject2;
            j = i;
            localJsonSerializer1.serializeWithType(localObject2, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
          }
        }
      }
      catch (IOException paramArrayOfObject)
      {
        throw paramArrayOfObject;
      }
      catch (Exception paramArrayOfObject)
      {
        if (((paramArrayOfObject instanceof InvocationTargetException)) && (paramArrayOfObject.getCause() != null))
        {
          paramArrayOfObject = paramArrayOfObject.getCause();
          continue;
        }
        if ((paramArrayOfObject instanceof Error)) {
          throw ((Error)paramArrayOfObject);
        }
        throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject1, j);
      }
      return;
      i += 1;
    }
  }
  
  public ObjectArraySerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    if ((_property == paramBeanProperty) && (paramJsonSerializer == _elementSerializer) && (_valueTypeSerializer == paramTypeSerializer) && (_unwrapSingle == paramBoolean)) {
      return this;
    }
    return new ObjectArraySerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */