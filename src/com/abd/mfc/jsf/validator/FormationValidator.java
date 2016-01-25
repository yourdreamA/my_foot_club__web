package com.abd.mfc.jsf.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.TypeMessage;

@FacesValidator("formationValidator")
public class FormationValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		/*List<MessageVO> list = new ArrayList<MessageVO>();
		
		//controle tous joueurs saisis
		if (equipeUser == null || equipeUser.getFormation() == null) {
			list.add(new MessageVO(TypeMessage.ERROR, "saisi invalide."));
		} else {
			if (equipeUser.getGardien() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "gardien invalide."));
			}
			
			if (equipeUser.getDefenseur1() == null || equipeUser.getDefenseur2() == null
					 || equipeUser.getDefenseur3() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "defenseurs invalides. Vérifiez votre saisi."));
			}
			if (equipeUser.getMilieu1() == null || equipeUser.getMilieu2() == null
					 || equipeUser.getMilieu3() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "milieu invalides. Vérifiez votre saisi."));
			}
			
			if (equipeUser.getAtt1() == null || equipeUser.getAtt2() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "attaquant invalides. Vérifiez votre saisi."));
			}
				
			switch (equipeUser.getFormation()) {
			case Q_Q_D:
				if (equipeUser.getDefenseur4() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "defenseurs invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getMilieu4() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "milieu invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getAtt3() != null) {//invalide
					list.add(new MessageVO(TypeMessage.ERROR, "attaquant invalides. Vérifiez votre saisi."));
				}
				break;
			case Q_T_T:
				if (equipeUser.getDefenseur4() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "defenseurs invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getMilieu4() != null) {//invalide
					list.add(new MessageVO(TypeMessage.ERROR, "milieu invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getAtt3() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "attaquant invalides. Vérifiez votre saisi."));
				}
				break;
			
			case T_Q_T:
				if (equipeUser.getDefenseur4() != null) {//invalide
					list.add(new MessageVO(TypeMessage.ERROR, "defenseurs invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getMilieu4() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "milieu invalides. Vérifiez votre saisi."));
				}
				if (equipeUser.getAtt3() == null) {
					list.add(new MessageVO(TypeMessage.ERROR, "attaquant invalides. Vérifiez votre saisi."));
				}
				break;

			default:
				break;
			}
		}
		
		if (list.isEmpty()) {
			int sommeJ = 0;
			Map<Long, Integer> mapEquipe = new HashMap<Long, Integer>();
			
			sommeJ = putJ(mapEquipe, equipeUser.getGardien(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur3(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur4(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu3(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu4(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getAtt1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getAtt2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getAtt3(), sommeJ);
			
			//verification solde par somme joueurs
			if (sommeJ > equipeUser.getSoldeUser()) {
				list.add(new MessageVO(TypeMessage.ERROR, "Solde insuffisant."));
			}
			//verification 3 joueurs par equipe
			for (Long key : mapEquipe.keySet()) {
				if (mapEquipe.get(key) > 3) {
					list.add(new MessageVO(TypeMessage.ERROR, "Maximum joueurs par équipe : 3."));
				}
			}
		}
		
		return list;*/
	}

}
