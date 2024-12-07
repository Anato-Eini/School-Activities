using AutoMapper;
using ANI.Models;
using ANI.DTO;

namespace ANI.Mappings;
public class MappingProfile : Profile
{
    public MappingProfile()
    {
        CreateMap<User, UserResponseDTO>();
        CreateMap<UserCreateDTO, User>();
    }
}