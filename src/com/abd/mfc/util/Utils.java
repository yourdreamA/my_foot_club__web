package com.abd.mfc.util;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.hibernate.criterion.Restrictions;

import ch.lambdaj.function.matcher.HasArgumentWithValue;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.JoueurVO;

public class Utils {
	
	public static List cloneList(List source) {
		List list = null;
		if (!isEmpty(source)) {
			list = new ArrayList(source.size());
			
			for (Object item : source) {
				list.add(item);
			}
		}
		
		return list;
	}
	
	public static List<JoueurVO> filterJoueur(List<JoueurVO> sourceList, JoueurVO filter) throws ApplicationAbdException {

		if (isEmpty(sourceList)) {
			throw new ApplicationAbdException("Problem list source!!");
		}
		
		List<JoueurVO> resultat = null;
		
		if (filter.getIdChampionnat() > 0) {
			resultat = select(sourceList,
					having(on(JoueurVO.class).getIdChampionnat(),
							Matchers.equalTo(filter.getIdChampionnat())));
		}
		
		if (filter.getIdEquipe() > 0) {
			HasArgumentWithValue<Object, Long> matcherEquipe = having(on(JoueurVO.class).getIdEquipe(),
					Matchers.equalTo(filter.getIdEquipe()));
			
			if (isEmpty(resultat)) {
				resultat = select(sourceList, matcherEquipe);
			} else {
				resultat = select(resultat, matcherEquipe);
			}
			
		}
		
		if (filter.getPosition() > 0) {
			HasArgumentWithValue<Object, Integer> matcherPos = having(on(JoueurVO.class).getPosition(),
					Matchers.equalTo(filter.getPosition()));
			
			if (isEmpty(resultat)) {
				resultat = select(sourceList, matcherPos);
			} else {
				resultat = select(resultat, matcherPos);
			}
			
		}
		
		return resultat;
	
	}
	
	public static boolean isEmpty(Collection list) {
		return list == null || list.isEmpty();
	}
	
	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

}
