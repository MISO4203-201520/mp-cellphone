package co.edu.uniandes.csw.mpcellphone.converters;

import co.edu.uniandes.csw.mpcellphone.dtos.UserDTO;
import co.edu.uniandes.csw.mpcellphone.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class UserConverter {
    
    /**
     * @generated
     */
    private UserConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static UserDTO refEntity2DTO(UserEntity entity) {
        if (entity != null) {
            
            UserDTO dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            //dto.setUserId(entity.getUserId());
            dto.setRole(entity.getRole()); 
            //dto.setEmail(entity.getEmail());
            dto.setStormpath(entity.getStormpath());
            dto.setPassword(entity.getPassword());
   
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return
     * @generated
     */
    public static UserEntity refDTO2Entity(UserDTO dto) {
        if (dto != null) {
            UserEntity entity = new UserEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static UserDTO basicEntity2DTO(UserEntity entity) {
        if (entity != null) {
            UserDTO dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            //dto.setUserId(entity.getUserId());
            // dto.setEmail(entity.getEmail());
            dto.setRole(entity.getRole()); 
            dto.setStormpath(entity.getStormpath());
            dto.setPassword(entity.getPassword());        
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static UserEntity basicDTO2Entity(UserDTO dto) {
        if (dto != null) {
            UserEntity entity = new UserEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            //entity.setUserId(dto.getUserId());
            //entity.setEmail(dto.getEmail());
            entity.setRole(dto.getRole());
            entity.setStormpath(dto.getStormpath());
            entity.setPassword(dto.getPassword());
         
            return entity;
        } else {
            return null;
        }
    }


    /**
     * @generated
     */

    public static List<UserDTO> listEntity2DTO(List<UserEntity> entities) {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        if (entities != null) {
            for (UserEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<UserEntity> listDTO2Entity(List<UserDTO> dtos) {
        List<UserEntity> entities = new ArrayList<UserEntity>();
        if (dtos != null) {
            for (UserDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
