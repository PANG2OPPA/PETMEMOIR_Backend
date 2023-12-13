package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.PetProfileDto;
import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.entity.PetProfile;
import com.Doggo.DoggoEx.repository.MemberRepository;
import com.Doggo.DoggoEx.repository.PetProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetProfileService {
    private final PetProfileRepository petProfileRepository;
    private final MemberRepository memberRepository;

    // 반려동물 등록
    public boolean savePetProfile(PetProfileDto petProfileDto) {
        try {
            PetProfile petProfile = new PetProfile();
            Member member = memberRepository.findByMemberEmail(petProfileDto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            petProfile.setMember(member);
            petProfile.setPetName(petProfileDto.getPetName());
            petProfile.setAnimalType(petProfileDto.getAnimalType());
            petProfile.setBreed(petProfileDto.getBreed());
            petProfile.setImageLink(petProfileDto.getImageLink());
            petProfile.setDetail(petProfileDto.getDetail());
            petProfile.setBirthDate(petProfileDto.getBirthDate());
            petProfileRepository.save(petProfile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 이메일로 반려동물 조회
    public List<PetProfileDto> getPetProfileByEmail(String email) {
        List<PetProfile> petProfiles = petProfileRepository.findByMember_MemberEmail(email);
        List<PetProfileDto> petProfileDtos = new ArrayList<>();
        for(PetProfile petProfile : petProfiles) {
            petProfileDtos.add(convertEntityToDto(petProfile));
        }
        return petProfileDtos;
    }

    // 반려동물 전체 조회
    public List<PetProfileDto> getPetProfileList() {
        List<PetProfile> petProfiles = petProfileRepository.findAll();
        List<PetProfileDto> petProfileDtos = new ArrayList<>();
        for(PetProfile petProfile : petProfiles) {
            petProfileDtos.add(convertEntityToDto(petProfile));
        }
        return petProfileDtos;
    }

    private PetProfileDto convertEntityToDto(PetProfile petProfile) {
        PetProfileDto petProfileDto = new PetProfileDto();
        petProfileDto.setId(petProfile.getId());
        petProfileDto.setMemberId(petProfile.getMember().getMemberEmail());
        petProfileDto.setAnimalType(petProfile.getAnimalType());
        petProfileDto.setPetName(petProfile.getPetName());
        petProfileDto.setBreed(petProfile.getBreed());
        petProfileDto.setImageLink(petProfile.getImageLink());
        petProfileDto.setDetail(petProfile.getDetail());
        petProfileDto.setBirthDate(petProfile.getBirthDate());
        petProfileDto.setRegDate(petProfile.getRegDate());
        return petProfileDto;
    }
}
